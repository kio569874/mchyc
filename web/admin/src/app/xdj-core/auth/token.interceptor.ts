import {
    HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest,
    HttpResponse
} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {TokenService} from "./token.service";
import {Router} from "@angular/router";
import {NzMessageService} from "ng-zorro-antd";
import {catchError, mergeMap} from 'rxjs/operators';
import {of} from "rxjs/observable/of";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

    constructor(private tokenService: TokenService, private router: Router, private msg: NzMessageService) {}

    private goTo(url: string) {
        setTimeout(() => this.router.navigateByUrl(url));
    }

    private handleData(event: HttpResponse<any> | HttpErrorResponse): Observable<any> {
        // 业务处理：一些通用操作
        switch (event.status) {
            case 200:
                // 业务层级错误处理，以下假如响应体的 `status` 若不为 `0` 表示业务级异常
                // 并显示 `error_message` 内容

                // const body: any = event instanceof HttpResponse && event.body;
                // if (body && body.status !== 0) {
                //     this.msg.error(body.error_message);
                //     // 继续抛出错误中断后续所有 Pipe、subscribe 操作，因此：
                //     // this.http.get('/').subscribe() 并不会触发
                //     return ErrorObservable.throw(event);
                // }
                break;
            case 401: // 未登录状态码
                this.goTo('/xdjpassport/login');
                break;
            case 403:
            case 404:
            case 500:
                this.goTo(`/${event.status}`);
                break;
        }
        return of(event);
    }
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (this.tokenService.exsistToken()){
            req = req.clone({
                setHeaders: {
                    Authorization: this.tokenService.getToken()
                }
            });
        }
        return next.handle(req).pipe(
            mergeMap((event: any) => {
                // 允许统一对请求错误处理，这是因为一个请求若是业务上错误的情况下其HTTP请求的状态是200的情况下需要
                if (event instanceof HttpResponse && event.status === 200)
                    return this.handleData(event);
                // 若一切都正常，则后续操作
                return of(event);
            }),
            catchError((err: HttpErrorResponse) => this.handleData(err))
        );
    }
}
