import { Component, OnInit } from '@angular/core';
import {NzMessageService, NzModalService} from 'ng-zorro-antd';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { tap } from 'rxjs/operators';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { environment } from '@env/environment';
import {HttpSender} from "../../../../xdj-core/net/http.service";
import {Loading} from "../../../../xdj-core/net/loading.model";

@Component({
    selector: 'product-introduce-list',
    templateUrl: './introduce-list.component.html'
})
export class ProductIntroduceListComponent implements OnInit {
    form: FormGroup;
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
    sortMap: any = {};
    expandForm = false;
    modalVisible = false;

    informationId : string;
    informationName : string;
    informationContent: string;
    informationDesc: string;

    constructor(private http: HttpSender, private fb: FormBuilder, public msg: NzMessageService,private modal: NzModalService) {}

    ngOnInit() {
        this.getData();
        this.form = this.fb.group({
            informationName: [null, [Validators.required]],
            informationContent: [null, [Validators.required]],
            informationDesc: [null, [Validators.required]],
        }, );
    }

    getData() {
        this.http.post('/product/productInformation/query', this.loading, null, this.q.page, this.q.pageSize).then(res => {
            this.data = res.data;
            this.totalCount = res.totalCount;
        });
    }

    add() {
        this.modalVisible = true;
        this.informationId = '';
        this.informationName = '';
        this.informationContent = '';
        this.informationDesc = '';
    }

    save() {
        let action;
        if (this.form.invalid) {
            this.msg.error('数据填写有误,请仔细检查!');
            return;
        }
        const body = {
            informationName: this.informationName,
            informationContent: this.informationContent,
            informationDesc: this.informationDesc,
        };
        if(this.informationId !== ''){
            body['informationId'] = this.informationId;
            action = 'update';
        }else{
            action = 'insert';
        }
        this.http.post( '/product/productInformation/' + action, this.loading, body).then(() => {
            this.getData();
            setTimeout(() => this.modalVisible = false, 500);
        });
    }

    edit(information){
        this.modalVisible = true;
        this.informationId = information.informationId;
        this.informationName = information.informationName;
        this.informationContent = information.informationContent;
        this.informationDesc = information.informationDesc;
    }

    deleteAll() {
    }

    delete(id) {
        this.modal.open({
            title: '确认删除',
            content: '是否确认删除?',
            okText: '确认',
            cancelText: '取消',
            onOk: () => {
                this.http.post('/product/productInformation/delete', this.loading, { informationId : id}).then(() => {
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
