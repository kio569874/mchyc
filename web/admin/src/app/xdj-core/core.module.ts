import { NgModule} from '@angular/core';

import { HttpSender } from './net/http.service';
import {TokenService} from "./auth/token.service";

@NgModule({
    providers: [
        HttpSender,
        TokenService
    ]
})
export class XdjCoreModule {}
