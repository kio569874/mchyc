import {Injectable} from "@angular/core";
import {StringUtil} from "../util/string.util";

@Injectable()
export class TokenService {
    private store: object;
    public static login_url: string = '/xdjpassport/login';
    constructor() {
        this.store = localStorage;
    }
    public saveToken(token: string): void {
        this.store['token'] = token;
    }

    public clearToken(): void {
        this.store['token'] = '';
    }

    public getToken(): string {
        return this.store['token'];
    }

    public exsistToken(): boolean {
        return StringUtil.isNotEmpty(this.store['token']);
    }
}
