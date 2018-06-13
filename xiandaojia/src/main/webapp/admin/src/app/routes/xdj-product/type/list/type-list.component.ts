import { Component, OnInit } from '@angular/core';
import {NzMessageService, NzModalService} from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { tap } from 'rxjs/operators';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
<<<<<<< HEAD
import {environment} from "@env/environment";
=======
>>>>>>> master

@Component({
    selector: 'product-type-list',
    templateUrl: './type-list.component.html'
})
export class ProductTypeListComponent implements OnInit {
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

    bigtypeId = '';
    bigtypeName = '';
    bigtypeSeqno: number;
    bigtypeStatus: string;

<<<<<<< HEAD
    baseUrl : string = environment.XDJ_SERVER_URL;
=======
    baseUrl : string = 'http://localhost:8999/xiandaojia';
>>>>>>> master

    constructor(private http: _HttpClient, private fb: FormBuilder, public msg: NzMessageService,private modal: NzModalService) {}

    ngOnInit() {
        this.getData();
        this.form = this.fb.group({
            bigtypeName: [null, [Validators.required]],
            bigtypeSeqno: [null, [Validators.required]],
            bigtypeStatus: [null, [Validators.required]],
        }, );
    }

    getData() {
        this.loading = true;
        this.q.statusList = this.status.map((i, index) => i.value ? index : -1).filter(w => w !== -1);
        if (this.q.status !== null && this.q.status > -1) this.q.statusList.push(this.q.status);
        this.http.post(this.baseUrl + '/product/productBigTypeInfo/query', this.q).pipe(
            tap((res: any) => {
<<<<<<< HEAD
                return res.data.data.map(i => {
=======
                debugger;
                return res.data.listData.map(i => {
>>>>>>> master
                    const statusItem = this.status[i.status];
                    i.statusText = statusItem.text;
                    i.statusType = statusItem.type;
                    return i;
                });
            })
        ).subscribe(res =>{
<<<<<<< HEAD
            this.data = res.data.data;
            this.totalCount = res.data.totalCount;
=======
            debugger;
            this.data = res.data.listData;
            this.totalCount = 12;
>>>>>>> master
            this.loading = false;}
        );
    }

    add() {
        this.modalVisible = true;
        this.bigtypeId = '';
        this.bigtypeName = '';
        this.bigtypeSeqno = 0;
        this.bigtypeStatus = this.status[0].value;
    }

    save() {
        let action;
        if (this.form.invalid) {
<<<<<<< HEAD
            this.msg.error('数据填写有误,请仔细检查!');
=======
            alert(1);
>>>>>>> master
            return;
        }
        this.loading = true;
        const body = {
                        bigtypeName: this.bigtypeName,
                        bigtypeSeqno: this.bigtypeSeqno,
                        status: this.bigtypeStatus,
                      };
        if(this.bigtypeId !== ''){
            body['bigtypeId'] = this.bigtypeId;
            action = 'update';
        }else{
            action = 'insert';
        }
        this.http.post(this.baseUrl + '/product/productBigTypeInfo/'+ action, body).subscribe(() => {
            this.getData();
            setTimeout(() => this.modalVisible = false, 500);
        });
    }

    edit(type){
        debugger;
        this.modalVisible = true;
        this.bigtypeId = type.bigtypeId;
        this.bigtypeName = type.bigtypeName;
        this.bigtypeSeqno = type.bigtypeSeqno;
        this.bigtypeStatus = type.status;
    }

    deleteAll() {
        this.http.post(this.baseUrl + '/product/productBigTypeInfo/delete', { nos: this.selectedRows.map(i => i.no).join(',') }).subscribe(() => {
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
                this.http.post(this.baseUrl + '/product/productBigTypeInfo/delete', { bigtypeId : id}).subscribe(() => {
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
