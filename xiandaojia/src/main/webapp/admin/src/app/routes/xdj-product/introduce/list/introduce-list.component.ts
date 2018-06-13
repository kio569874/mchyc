import { Component, OnInit } from '@angular/core';
<<<<<<< HEAD
import {NzMessageService, NzModalService} from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { tap } from 'rxjs/operators';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { environment } from '@env/environment';
=======
import { NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { tap } from 'rxjs/operators';
>>>>>>> master

@Component({
    selector: 'product-introduce-list',
    templateUrl: './introduce-list.component.html'
})
export class ProductIntroduceListComponent implements OnInit {
<<<<<<< HEAD
    form: FormGroup;
    q: any = {
        page: 1,
        pageSize: 10,
=======
    q: any = {
        pi: 1,
        ps: 10,
>>>>>>> master
        sorter: '',
        status: null,
        statusList: []
    };
<<<<<<< HEAD
    totalCount : number = 0;
=======
>>>>>>> master
    data: any[] = [];
    loading = false;
    selectedRows: any[] = [];
    curRows: any[] = [];
    totalCallNo = 0;
    allChecked = false;
    indeterminate = false;
<<<<<<< HEAD
    sortMap: any = {};
    expandForm = false;
    modalVisible = false;

    informationId : string;
    informationName : string;
    informationContent: string;
    informationDesc: string;

    baseUrl : string = environment.XDJ_SERVER_URL;

    constructor(private http: _HttpClient, private fb: FormBuilder, public msg: NzMessageService,private modal: NzModalService) {}

    ngOnInit() {
        this.getData();
        this.form = this.fb.group({
            informationName: [null, [Validators.required]],
            informationContent: [null, [Validators.required]],
        }, );
    }

    getData() {
        this.loading = true;
        this.http.post(this.baseUrl + '/product/productInformation/query', this.q).pipe(
            tap((res: any) => {
            })
        ).subscribe(res =>{
            this.data = res.data.data;
            this.totalCount = res.data.totalCount;
            this.loading = false;}
        );
=======
    status = [
        { text: '关闭', value: false, type: 'default' },
        { text: '运行中', value: false, type: 'processing' },
        { text: '已上线', value: false, type: 'success' },
        { text: '异常', value: false, type: 'error' }
    ];
    sortMap: any = {};
    expandForm = false;
    modalVisible = false;
    description = '';

    constructor(private http: _HttpClient, public msg: NzMessageService) {}

    ngOnInit() {
        this.getData();
    }

    getData() {
        this.pageChange(1).then(() => {
            this.q.statusList = this.status.map((i, index) => i.value ? index : -1).filter(w => w !== -1);
            if (this.q.status !== null && this.q.status > -1) this.q.statusList.push(this.q.status);
            this.http.get('/rule', this.q).pipe(
                tap((list: any[]) => {
                    return list.map(i => {
                        const statusItem = this.status[i.status];
                        i.statusText = statusItem.text;
                        i.statusType = statusItem.type;
                        return i;
                    });
                })
            ).subscribe(res => this.data = res);
        });
>>>>>>> master
    }

    add() {
        this.modalVisible = true;
<<<<<<< HEAD
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
=======
        this.description = '';
    }

    save() {
        this.loading = true;
        this.http.post('/rule', { description: this.description }).subscribe(() => {
>>>>>>> master
            this.getData();
            setTimeout(() => this.modalVisible = false, 500);
        });
    }

<<<<<<< HEAD
    edit(information){
        this.modalVisible = true;
        this.informationId = information.informationId;
        this.informationName = information.informationName;
        this.informationContent = information.informationContent;
        this.informationDesc = information.informationDesc;
    }

    deleteAll() {
        this.http.post(this.baseUrl + '/product/productInformation/delete', { nos: this.selectedRows.map(i => i.no).join(',') }).subscribe(() => {
=======
    remove() {
        this.http.delete('/rule', { nos: this.selectedRows.map(i => i.no).join(',') }).subscribe(() => {
>>>>>>> master
            this.getData();
            this.clear();
        });
    }

<<<<<<< HEAD
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

=======
>>>>>>> master
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

<<<<<<< HEAD
    pageChange(page: number){
        this.q.page = page;
        this.getData();
=======
    pageChange(pi: number): Promise<any> {
        this.q.pi = pi;
        this.loading = true;
        return new Promise((resolve) => {
            setTimeout(() => {
                this.loading = false;
                resolve();
            }, 500);
        });
>>>>>>> master
    }

    reset(ls: any[]) {
        for (const item of ls) item.value = false;
        this.getData();
    }
}
