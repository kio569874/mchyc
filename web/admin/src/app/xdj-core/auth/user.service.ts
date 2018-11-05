import {Injectable} from "@angular/core";
import {StringUtil} from "../util/string.util";

@Injectable()
export class UserService {
    private store: object;
    constructor() {
        this.store = localStorage;
    }
    public saveUser(user: any): void {
        this.store['user'] = JSON.stringify(user);
    }

    public clearUser(): void {
        this.store['user'] = '';
    }

    public getUser(): any {
        if(this.exsistUser()){
            return JSON.parse(this.store['user']);
        }
        return null;
    }

    public exsistUser(): boolean {
        return StringUtil.isNotEmpty(this.store['user']);
    }
}
