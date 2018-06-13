import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { ProductRoutingModule } from './product-routing.module';
import { ProductProductListComponent } from './product/list/product-list.component';
import { ProductProductFormComponent } from './product/form/product-form.component';
import { ProductIntroduceListComponent } from './introduce/list/introduce-list.component';
<<<<<<< HEAD
import { ProductTagListComponent } from './tag/list/tag-list.component';
import { ProductTypeListComponent } from './type/list/type-list.component';
import {ModelCustomComponent} from "../elements/modal/custom.component";
=======
import { ProductIntroduceFormComponent } from './introduce/form/introduce-form.component';
import { ProductTagListComponent } from './tag/list/tag-list.component';
import { ProductTagFormComponent } from './tag/form/tag-form.component';
import { ProductTypeListComponent } from './type/list/type-list.component';
import { ProductTypeFormComponent } from './type/form/type-form.component';
>>>>>>> master

@NgModule({
    imports: [ SharedModule, ProductRoutingModule ],
    declarations: [
        ProductProductListComponent,
        ProductProductFormComponent,
        ProductIntroduceListComponent,
<<<<<<< HEAD
        ProductTagListComponent,
        ProductTypeListComponent
    ],
    entryComponents: [
        ProductProductFormComponent
=======
        ProductIntroduceFormComponent,
        ProductTagListComponent,
        ProductTagFormComponent,
        ProductTypeListComponent,
        ProductTypeFormComponent
>>>>>>> master
    ]
})
export class ProductModule { }
