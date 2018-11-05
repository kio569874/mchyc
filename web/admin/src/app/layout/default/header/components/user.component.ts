import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { SettingsService } from '@delon/theme';
import {TokenService} from "../../../../xdj-core/auth/token.service";
import {UserService} from "../../../../xdj-core/auth/user.service";

@Component({
    selector: 'header-user',
    template: `
    <nz-dropdown nzPlacement="bottomRight">
        <div class="item d-flex align-items-center px-sm" nz-dropdown>
            <nz-avatar [nzSrc]="settings.user.avatar" nzSize="small" class="mr-sm"></nz-avatar>
            {{userService.getUser() == null ? '' : userService.getUser().userName}}
        </div>
        <div nz-menu class="width-sm">
            <div nz-menu-item [nzDisable]="true"><i class="anticon anticon-user mr-sm"></i>个人中心</div>
            <div nz-menu-item [nzDisable]="true"><i class="anticon anticon-setting mr-sm"></i>设置</div>
            <li nz-menu-divider></li>
            <div nz-menu-item (click)="logout()"><i class="anticon anticon-setting mr-sm"></i>退出登录</div>
        </div>
    </nz-dropdown>
    `
})
export class HeaderUserComponent implements OnInit {
    constructor(
        public settings: SettingsService,
        public userService: UserService,
        private router: Router,
        private tokenService: TokenService) {}

    ngOnInit(): void {
    }

    logout() {
        this.tokenService.clearToken();
        this.userService.clearUser();
        this.router.navigateByUrl(TokenService.login_url);
    }
}
