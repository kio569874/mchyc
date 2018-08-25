import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { ProductRoutingModule } from './product-routing.module';
import { ProductProductListComponent } from './product/list/product-list.component';
import { ProductProductFormComponent } from './product/form/product-form.component';
import { ProductIntroduceListComponent } from './introduce/list/introduce-list.component';
import { ProductTagListComponent } from './tag/list/tag-list.component';
import { ProductTypeListComponent } from './type/list/type-list.component';
import {ModelCustomComponent} from "../elements/modal/custom.component";

@NgModule({
    imports: [ SharedModule, ProductRoutingModule ],
    declarations: [
        ProductProductListComponent,
        ProductProductFormComponent,
        ProductIntroduceListComponent,
        ProductTagListComponent,
        ProductTypeListComponent
    ],
    entryComponents: [
        ProductProductFormComponent
    ]
})
export class ProductModule { }
