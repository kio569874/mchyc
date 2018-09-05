import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {NzMessageService, NzModalService, NzNotificationService} from 'ng-zorro-antd';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {environment} from "@env/environment";
import { tap } from 'rxjs/operators';
import * as moment from 'moment';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
import * as _ from 'lodash';
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
})
export class UserComponent implements OnInit {

    constructor(
        private http: HttpClient,
        private fb: FormBuilder,
        public msg: NzMessageService,
        private modal: NzModalService,
        private nznot: NzNotificationService,
    ) { }

    // 变量
    modalVisible = false; // 默认关闭
    roleAdd: RolesModule = new RolesModule();
    roleItem: RolesModule = new RolesModule();
    totalCount : number = 0;
    data: any[] = [];
    loading = false;
    selectedRows: any[] = [];
    curRows: any[] = [];
    totalCallNo = 0;
    allChecked = false;
    indeterminate = false;
    sortMap: any = {}; // 排序变量
    baseUrl : string = environment.XDJ_SERVER_URL; // url路径
    isEdit : boolean;
    roleTitle: string; // 弹出框名称
    isPassword: boolean; // 密码栏是否显示
    status = [
        { text: '正常', value: '0', type: 'success' },
        { text: '停用', value: '1', type: 'error' }
    ];

    q: any = {
        page: 1,
        pageSize: 10,
        sorter: '',
        status: null,
        statusList: []
    };

    dsa = []
    ngOnInit() {
        this.getData()
    }


    getData() {
        this.loading = true;
        this.q.statusList = this.status.map((i, index) => i.value ? index : -1).filter(w => w !== -1);
        if (this.q.status !== null && this.q.status > -1) this.q.statusList.push(this.q.status);
        this.http.post(this.baseUrl + '/user/systemUser/queryListByPage', this.q).pipe(
            tap((res: any) => {
                console.log(res)
                res.data = JSON.parse(res.data); // 先转一下, 晚上修改好接口，在改,把转换去掉即可
                return res.data.map(i => {
                    const statusItem = this.status[i.userStatus];
                    i.statusText = statusItem.text;
                    i.statusType = statusItem.type;
                    return i;
                });
            })
        ).subscribe(res =>{
                this.data = res.data;
                this.totalCount = res.totalCount;
                this.loading = false;
            }
        );
    }


    // 添加方法
    add() {
        this.modalVisible = true;
        this.roleTitle = '新增用户'
        this.isPassword = true;
        this.roleAdd = new RolesModule(); // 清空
        this.isEdit = false; // 不是修改
    }


    // 修改方法
    edit(item) {
        this.modalVisible = true;
        this.roleTitle = '修改用户';
        this.isPassword = false;
        this.isEdit = true; // 是修改
        this.roleAdd = item;
    }

    // 保存按钮
    save() {
        let objJson = this.roleAdd;
        if(_.isUndefined(objJson.createTime)) {
            objJson.createTime = moment(new Date()).format('YYYY-MM-DD'); // 默认当前时间
        }
        objJson.createTime = moment(objJson.createTime).format('YYYY-MM-DD')
        let objJsondata = {
            data: objJson
        }
        console.log(objJsondata)
        if(this.isEdit) { // 修改接口
            this.http.post(this.baseUrl + '/user/systemUser/update', objJsondata)
                .subscribe(
                    (val) =>{
                        this.modalVisible = false;
                        this.nznot.create('success', '修改成功', '修改成功');
                        this.getData();

                    },
                    (error) => {
                        this.modalVisible = false;
                        this.nznot.create('error', '修改' + error.retMsg , '修改' + error.retMsg);
                    });

        } else {
            this.http.post(this.baseUrl + '/user/systemUser/insert', objJsondata)
                .subscribe(
                    (val) =>{
                        this.modalVisible = false;
                        this.nznot.create('success', '新增成功' , '成功');
                        this.getData();
                    },
                    (error) => {
                        this.modalVisible = false;
                        this.nznot.create('error', error.msg , error.msg);
                    });
        }
    }

    // 删除方法
    delete(id) {
        let dataObj = {
            data: {id: id}
        }
        console.log(dataObj)
        this.modal.open({
            title: '确认删除',
            content: '是否确认删除该用户',
            okText: '确认',
            cancelText: '取消',
            onOk: () => {
                this.http.post(this.baseUrl + '/user/systemUser/delete', dataObj).subscribe(
                    (val) =>{
                        this.nznot.create('success', '删除成功' , '删除成功');
                        this.getData();
                    },
                    (error) => {
                        this.nznot.create('error', '删除失败', '删除失败');
                    });
            },
            onCancel: () => {
            }
        });

    }


    // 密码重置
    resetPassword(i) {
        let dataObj = {
            data: {id: i.id}
        }
        console.log(dataObj)
        this.modal.open({
            title: '确认删除',
            content: '是否重置用户(' + i.userName + ')的密码?',
            okText: '确认',
            cancelText: '取消',
            onOk: () => {
                this.http.post(this.baseUrl + '/user/systemUser/passWordReset', dataObj).subscribe(
                    (val) =>{
                        this.nznot.create('success', '密码重置成功' , '密码重置成功');
                    },
                    (error) => {
                        this.nznot.create('error', '密码重置失败', '密码重置失败');
                    });
            },
            onCancel: () => {
            }
        });
    }
    // 批量删除方法, 先弃用
    /*deleteAll() {
        console.log(this.selectedRows)
        this.http.post(this.baseUrl + '/user/systemUser/delete', { nos: this.selectedRows.map(i => i.no).join(',') }).subscribe(() => {
            this.getData();
            this.clear();
        });
    }*/

    // list方法
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
    }

    // 翻页方法
    pageChange(page: number){
        this.q.page = page;
        this.getData()
    }

    // 数据改变方法
    dataChange(res: any) {
        this.curRows = res;
        this.refreshStatus();
    }


    reset(ls: any[]) {
        for (const item of ls) item.value = false;
        this.getData();
    }
}


// 角色类型
export class RolesModule {

    // 用户代码
    public userCode: string;
    // 用户密码
    public userPassword: string;
    // 用户姓名
    public userName: string;
    // 手机号码
    public userPhone: string;
    // 用户级别
    public userLevel: string;
    // 用户职位
    public userPosition: string;
    // 用户状态
    public userStatus: string;
    // 创建时间
    public createTime: string;
    // id
    public id: string;

}
