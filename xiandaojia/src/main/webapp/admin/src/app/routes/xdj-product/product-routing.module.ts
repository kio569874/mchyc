import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductTypeListComponent } from './type/list/type-list.component';
<<<<<<< HEAD
import { ProductTagListComponent } from './tag/list/tag-list.component';
import { ProductIntroduceListComponent } from './introduce/list/introduce-list.component';
import { ProductProductListComponent } from './product/list/product-list.component';
=======
import { ProductTypeFormComponent } from './type/form/type-form.component';
import { ProductTagListComponent } from './tag/list/tag-list.component';
import { ProductTagFormComponent } from './tag/form/tag-form.component';
import { ProductIntroduceListComponent } from './introduce/list/introduce-list.component';
import { ProductIntroduceFormComponent } from './introduce/form/introduce-form.component';
import { ProductProductListComponent } from './product/list/product-list.component';
import { ProductProductFormComponent } from './product/form/product-form.component';
>>>>>>> master

const routes: Routes = [
    {
        path: 'type/list',
        component: ProductTypeListComponent,
    },
    {
<<<<<<< HEAD
        path: 'tag/list', component: ProductTagListComponent ,
    },
    {
        path: 'introduce/list', component: ProductIntroduceListComponent,
    },
    {
        path: 'product/list', component: ProductProductListComponent,
=======
        path: 'type/form',
        component: ProductTypeFormComponent
    },
    {
        path: 'tag', component: ProductTagListComponent ,
        children: [
            { path: 'form/:id', component: ProductTagFormComponent},
        ]
    },
    {
        path: 'introduce', component: ProductIntroduceListComponent,
        children: [
            { path: 'form/:id', component: ProductIntroduceFormComponent},
        ]
    },
    {
        path: 'product', component: ProductProductListComponent,
        children: [
            { path: 'form/:id', component: ProductProductFormComponent},
        ]
>>>>>>> master
    }
];

@NgModule({
  imports: [ RouterModule.forChild(routes) ],
  exports: [ RouterModule ]
})
export class ProductRoutingModule { }
