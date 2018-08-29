import { Component, OnInit } from '@angular/core';
import {NzMessageService, NzModalService} from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { tap } from 'rxjs/operators';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {environment} from "@env/environment";

@Component({
    selector: 'product-tag-list',
    templateUrl: './tag-list.component.html'
})
export class ProductTagListComponent implements OnInit{
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
    status = [
        { text: '启用', value: '0', type: 'success' },
        { text: '停用', value: '1', type: 'error' }
    ];
    sortMap: any = {};
    expandForm = false;
    modalVisible = false;
    smallTypeId = '';
    smalltypeName = '';
    smallSeqno: number;
    smallTypeStatus: string;

    baseUrl : string = environment.XDJ_SERVER_URL;

    constructor(private http: _HttpClient, private fb: FormBuilder, public msg: NzMessageService,private modal: NzModalService) {}

    ngOnInit() {
        this.getData();
        this.form = this.fb.group({
            smalltypeName: [null, [Validators.required]],
            smallSeqno: [null, [Validators.required]],
            smallTypeStatus: [null, [Validators.required]],
        }, );
    }

    getData() {
        this.loading = true;
        this.q.statusList = this.status.map((i, index) => i.value ? index : -1).filter(w => w !== -1);
        if (this.q.status !== null && this.q.status > -1) this.q.statusList.push(this.q.status);
        this.http.post(this.baseUrl + '/product/productSmallTypeInfo/query', this.q).pipe(
            tap((res: any) => {
                debugger;
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
        this.modalVisible = true;
        this.smallTypeId = '';
        this.smalltypeName = '';
        this.smallSeqno = 0;
        this.smallTypeStatus = this.status[0].value;
    }

    save() {
        let action;
        if (this.form.invalid) {
            this.msg.error('数据填写有误,请仔细检查!');
            return;
        }
        this.loading = true;
        const body = {
            smalltypeName: this.smalltypeName,
            smallSeqno: this.smallSeqno,
            status: this.smallTypeStatus,
        };
        if(this.smallTypeId !== ''){
            body['smallTypeId'] = this.smallTypeId;
            action = 'update';
        }else{
            action = 'insert';
        }
        this.http.post(this.baseUrl + '/product/productSmallTypeInfo/'+ action, body).subscribe(() => {
            this.getData();
            setTimeout(() => this.modalVisible = false, 500);
        });
    }

    edit(type){
        this.modalVisible = true;
        this.smallTypeId = type.smallTypeId;
        this.smalltypeName = type.smalltypeName;
        this.smallSeqno = type.smallSeqno;
        this.smallTypeStatus = type.status;
    }

    deleteAll() {
        this.http.post(this.baseUrl + '/product/productSmallTypeInfo/delete', { nos: this.selectedRows.map(i => i.no).join(',') }).subscribe(() => {
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
                this.http.post(this.baseUrl + '/product/productSmallTypeInfo/delete', { smallTypeId : id}).subscribe(() => {
                    this.getData();
                });
            },
            onCancel: () => {
            }
        });
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
