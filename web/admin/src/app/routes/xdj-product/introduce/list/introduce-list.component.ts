import { Component, OnInit } from '@angular/core';
import {NzMessageService, NzModalService} from 'ng-zorro-antd';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { tap } from 'rxjs/operators';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { environment } from '@env/environment';

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
    loading = false;
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

    baseUrl : string = environment.XDJ_SERVER_URL;

    constructor(private http: HttpClient, private fb: FormBuilder, public msg: NzMessageService,private modal: NzModalService) {}

    ngOnInit() {
        this.getData();
        this.form = this.fb.group({
            informationName: [null, [Validators.required]],
            informationContent: [null, [Validators.required]],
            informationDesc: [null, [Validators.required]],
        }, );
    }

    getData() {
        this.loading = true;
        this.http.post(this.baseUrl + '/product/productInformation/query', this.q, {headers: new HttpHeaders({}), withCredentials: true}).pipe(
            tap((res: any) => {
            })
        ).subscribe(res =>{
            debugger;
            this.data = res.data.data;
            this.totalCount = res.data.totalCount;
            this.loading = false;}
        );
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
        this.loading = true;
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
        this.http.post(this.baseUrl + '/product/productInformation/'+ action, body).subscribe(() => {
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
        this.http.post(this.baseUrl + '/product/productInformation/delete', { nos: this.selectedRows.map(i => i.no).join(',') }).subscribe(() => {
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
                this.http.post(this.baseUrl + '/product/productInformation/delete', { informationId : id}).subscribe(() => {
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
