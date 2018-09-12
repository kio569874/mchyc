import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {Injectable, Injector} from "@angular/core";
import {environment} from "@env/environment";
import {NzMessageService} from "ng-zorro-antd";
import {catchError, map, tap} from "rxjs/operators";
import {Loading} from "./loading.model";

@Injectable()
export class HttpSender {
    private baseUrl: string = environment.XDJ_SERVER_URL;
    private successCode: string = '000000';
    constructor(
        private http: HttpClient,
        private msg: NzMessageService,
        private injector: Injector) {}

    private toReqBody(data: Object, page: number, pageSize: number): any {
        const body = {
            page: page,
            pageSize: pageSize,
            data: data
        };
        return body;
    }

    private handleError(error: HttpErrorResponse | any) {
        if (error.error instanceof ErrorEvent) {
            console.error('An error occurred:', error.error.message);
        } else {
            console.error(
                `Backend returned code ${error.status}, ` +
                `body was: ${error.error}`);
        }
        if (error.stack) {
            console.error(error.stack);
        }
        return'系统发生未知错误,请重新操作!';
    }

    post(url: string, loading: Loading, data: any | null, page?: number, pageSize?: number): Promise<any> {
        loading.start();
        return new Promise((resolve, reject) => {
            this.http.post(this.baseUrl + url, this.toReqBody(data, page, pageSize)).pipe(
                map((res: any) => {
                    return res;
                })
            ).subscribe(res => {
                if (res.retCode === this.successCode) {
                    loading.stop();
                    resolve(res);
                } else {
                    this.msg.error(res.retMsg + '\r\n' + '参考代码:' + res.retCode);
                    loading.stop();
                    reject(res);
                }
            }, error => {
                this.msg.error(this.handleError(error));
                loading.stop();
                reject();
            });
        });
    }

    postSilence(url: string, data: any | null, page?: number, pageSize?: number): Promise<any> {
        return new Promise((resolve, reject) => {
            this.http.post(this.baseUrl + url, this.toReqBody(data, page, pageSize)).pipe(
                map((res: any) => {
                    return res;
                }),
                catchError(this.handleError)
            ).subscribe(res => {
                if (res.retCode === this.successCode) {
                    resolve(res);
                } else {
                    reject();
                }
            }, error => {
                reject();
            });
        });
    }
}
