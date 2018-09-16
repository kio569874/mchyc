import { Component, OnInit } from '@angular/core';
import {NzMessageService, NzModalService} from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { tap } from 'rxjs/operators';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {environment} from "@env/environment";
import {ProductProductFormComponent} from "../form/product-form.component";
import {Loading} from "../../../../xdj-core/net/loading.model";
import {HttpSender} from "../../../../xdj-core/net/http.service";

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
    loading = new Loading();
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

    constructor(private http: HttpSender, private fb: FormBuilder, public msg: NzMessageService,private modal: NzModalService) {}

    ngOnInit() {
        this.getData();
    }

    getData() {
        this.q.statusList = this.status.map((i, index) => i.value ? index : -1).filter(w => w !== -1);
        if (this.q.status !== null && this.q.status > -1) this.q.statusList.push(this.q.status);
        this.http.post('/product/productInfo/query', this.loading, null, this.q.page, this.q.pageSize).then(res => {
            this.data = res.data.map(i => {
                const statusItem = this.status[i.status];
                i.statusText = statusItem.text;
                i.statusType = statusItem.type;
                return i;
            });
            this.totalCount = res.totalCount;
        });
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
        this.http.post('/product/productInfo/delete', this.loading, { nos: this.selectedRows.map(i => i.no).join(',') }).then(() => {
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
                this.http.post('/product/productInfo/delete', this.loading, { productId : id}).then(() => {
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
