import { Component, OnDestroy, Inject, Optional } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd';
import {HttpSender} from "../../../xdj-core/net/http.service";
import {Loading} from "../../../xdj-core/net/loading.model";
import {Md5} from "ts-md5/dist/md5";
import {TokenService} from "../../../xdj-core/auth/token.service";
import {UserService} from "../../../xdj-core/auth/user.service";
@Component({
    selector: 'passport-login',
    templateUrl: './login.component.html',
    styleUrls: [ './login.component.less' ]
})
export class XDJUserLoginComponent {


    form: FormGroup;
    error = '';
    loading = new Loading();

    constructor(
        fb: FormBuilder,
        private http: HttpSender,
        private router: Router,
        private tokenService: TokenService,
        private userService: UserService,
        public msg: NzMessageService) {
        this.form = fb.group({
            userName: [null, [Validators.required, Validators.minLength(5)]],
            password: [null, Validators.required],
            remember: [true]
        });
    }

    get userName() { return this.form.controls.userName; }
    get password() { return this.form.controls.password; }

    submit() {
        this.error = '';
        this.userName.markAsDirty();
        this.password.markAsDirty();
        if (this.userName.invalid || this.password.invalid) return;
        const data = {
            userCode : this.userName.value,
            userPassword : Md5.hashStr(this.password.value).toString()
        };
        this.http.post('/user/systemUser/login', this.loading, data).then(res => {
            this.tokenService.saveToken(res.data.token);
            this.userService.saveUser(res.data.user);
            this.router.navigate(['/']);
        }).catch(error => {});
    }

}
