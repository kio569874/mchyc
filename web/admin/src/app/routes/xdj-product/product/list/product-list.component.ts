import { Component, OnInit } from '@angular/core';
import {NzMessageService, NzModalService} from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { tap } from 'rxjs/operators';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {environment} from "@env/environment";
import {ProductProductFormComponent} from "../form/product-form.component";

@Component({
    selector: 'product-product-list',
    templateUrl: './product-list.component.html'
})
export class ProductProductListComponent implements OnInit {
    options : {};
    q: any = {
        page: 1,
        pageSize: 10,
        sorter: '',
        status: null,
        statusList: []
    };
    totalCount : number = 0;
    data: any[] = [];
    loading = false;
    selectedRows: any[] = [];
    curRows: any[] = [];
    totalCallNo = 0;
    allChecked = false;
    indeterminate = false;
    status = [
        { text: '启用', value: '0', type: 'success' },
        { text: '停用', value: '1', type: 'error' }
    ];
    isDiscounts = [
        { text: '是', value: '0'},
        { text: '否', value: '1'}
    ];
    sortMap: any = {};
    expandForm = false;
    modalVisible = false;
    productId : string;
    productName : string;
    productPrice: number;
    productNum: number;
    priceUnit : string;
    isDiscount : string;
    productDiscount : number;
    productStatus: string;
    productDesc: string;

    baseUrl : string = environment.XDJ_SERVER_URL;

    constructor(private http: _HttpClient, private fb: FormBuilder, public msg: NzMessageService,private modal: NzModalService) {}

    ngOnInit() {
        this.getData();
    }

    getData() {
        this.loading = true;
        this.q.statusList = this.status.map((i, index) => i.value ? index : -1).filter(w => w !== -1);
        if (this.q.status !== null && this.q.status > -1) this.q.statusList.push(this.q.status);
        this.http.post(this.baseUrl + '/product/productInfo/query', this.q).pipe(
            tap((res: any) => {
                return res.data.data.map(i => {
                    const statusItem = this.status[i.status];
                    i.statusText = statusItem.text;
                    i.statusType = statusItem.type;
                    return i;
                });
            })
        ).subscribe(res =>{
            this.data = res.data.data;
            this.totalCount = res.data.totalCount;
            this.loading = false;}
        );
    }

    add() {
        this.options = {
            wrapClassName: 'modal-lg',
            content: ProductProductFormComponent,
            footer: false,
            componentParams: {
                productId_ : ''
            }
        };
        this.modal.open(this.options).subscribe(result => {
            if(result === '0'){
                this.msg.success(`新增产品成功`);
                this.getData();
            }
        });

    }

    edit(product){
        this.options = {
            wrapClassName: 'modal-lg',
            content: ProductProductFormComponent,
            footer: false,
            nzMaskClosable:false,
            componentParams: {
                productId_ : product.productId,
                bigtypeId_ : product.bigtypeId,
                productName_ : product.productName,
                productPrice_ : product.productPrice,
                productNum_ : product.productNum,
                priceUnit_ : product.priceUnit,
                isDiscount_ : product.isDiscount,
                productDiscount_ : product.productDiscount,
                productDesc_ : product.productDesc,
                productStatus_ : product.status,
            }
        };
        this.modal.open(this.options).subscribe(result => {
            if(result === '0'){
                this.msg.success(`修改产品成功`);
                this.getData();
            }
        });
    }

    deleteAll() {
        this.http.post(this.baseUrl + '/product/productInfo/delete', { nos: this.selectedRows.map(i => i.no).join(',') }).subscribe(() => {
            this.getData();
            this.clear();
        });
    }

    delete(id) {
        this.modal.open({
            title: '确认删除',
            content: '是否确认删除?',
            okText: '确认',
            cancelText: '取消',
            onOk: () => {
                this.http.post(this.baseUrl + '/product/productInfo/delete', { productId : id}).subscribe(() => {
                    this.getData();
                });
            },
            onCancel: () => {
            }
        });

    }
    approval() {
        this.msg.success(`审批了 ${this.selectedRows.length} 笔`);
    }

    clear() {
        this.selectedRows = [];
        this.totalCallNo = 0;
        this.data.forEach(i => i.checked = false);
        this.refreshStatus();
    }

    checkAll(value: boolean) {
        this.curRows.forEach(i => {
            if (!i.disabled) i.checked = value;
        });
        this.refreshStatus();
    }

    refreshStatus() {
        const allChecked = this.curRows.every(value => value.disabled || value.checked);
        const allUnChecked = this.curRows.every(value => value.disabled || !value.checked);
        this.allChecked = allChecked;
        this.indeterminate = (!allChecked) && (!allUnChecked);
        this.selectedRows = this.data.filter(value => value.checked);
        this.totalCallNo = this.selectedRows.reduce((total, cv) => total + cv.callNo, 0);
    }

    sort(field: string, value: any) {
        this.sortMap = {};
        this.sortMap[field] = value;
        this.q.sorter = value ? `${field}_${value}` : '';
        this.getData();
    }

    dataChange(res: any) {
        this.curRows = res;
        this.refreshStatus();
    }

    pageChange(page: number){
        this.q.page = page;
        this.getData();
    }

    reset(ls: any[]) {
        for (const item of ls) item.value = false;
        this.getData();
    }
}
