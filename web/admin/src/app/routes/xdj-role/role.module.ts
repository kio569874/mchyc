import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { RoleRoutingModule } from './role-routing.module';
import { RoleComponent } from './role/role.component';
import { UserComponent} from './user/user.component';
@NgModule({
    imports: [ SharedModule, RoleRoutingModule ],
    declarations: [
        RoleComponent,
        UserComponent
    ],
    entryComponents: [
    ]
})
export class RoleModule { }
