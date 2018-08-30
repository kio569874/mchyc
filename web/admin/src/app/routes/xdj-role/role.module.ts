import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { RoleRoutingModule } from './role-routing.module';
import { RoleComponent } from './role/role.component';

@NgModule({
    imports: [ SharedModule, RoleRoutingModule ],
    declarations: [
        RoleComponent
    ],
    entryComponents: [
    ]
})
export class RoleModule { }
