import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductTypeListComponent } from './type/list/type-list.component';
import { ProductTagListComponent } from './tag/list/tag-list.component';
import { ProductIntroduceListComponent } from './introduce/list/introduce-list.component';
import { ProductProductListComponent } from './product/list/product-list.component';

const routes: Routes = [
    {
        path: 'type/list',
        component: ProductTypeListComponent,
    },
    {
        path: 'tag/list', component: ProductTagListComponent ,
    },
    {
        path: 'introduce/list', component: ProductIntroduceListComponent,
    },
    {
        path: 'product/list', component: ProductProductListComponent,
    }
];

@NgModule({
  imports: [ RouterModule.forChild(routes) ],
  exports: [ RouterModule ]
})
export class ProductRoutingModule { }
