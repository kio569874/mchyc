import { Component, OnInit } from '@angular/core';
import { _HttpClient } from '@delon/theme';
import {NzMessageService, NzModalService, NzNotificationService} from 'ng-zorro-antd';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {environment} from "@env/environment";
import { tap } from 'rxjs/operators';
import * as moment from 'moment';


@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
})
export class RoleComponent implements OnInit {

    constructor(
        private http: _HttpClient,
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
       /* this.http.post(this.baseUrl + '/user/systemUser/queryListByPage', this.q)
            .subscribe(res =>{
                console.log(res)
           /!* this.data = res.data.data;
            this.totalCount = res.data.totalCount;
            this.loading = false;*!/
        });*/

        this.loading = true;
        this.q.statusList = this.status.map((i, index) => i.value ? index : -1).filter(w => w !== -1);
        if (this.q.status !== null && this.q.status > -1) this.q.statusList.push(this.q.status);
       this.data = [
           {id:1 ,userCode: 'role001', userPassword: '123456', userName: '汪波', userPhone: 13003504858, userLevel: 1, userPosition: '测试', userStatus: '0', createTime: '2018-08-29'},
           {id:1 ,userCode: 'role002', userPassword: '123456', userName: '赵春海', userPhone: 13003504858, userLevel: 1, userPosition: '测试', userStatus: '0', createTime: '2018-08-29'},
           {id:1 ,userCode: 'role003', userPassword: '123456', userName: '汪波', userPhone: 13003504858, userLevel: 1, userPosition: '测试', userStatus: '1', createTime: '2018-08-26'},
           {id:1 ,userCode: 'role004', userPassword: '123456', userName: '李俊华', userPhone: 13003504858, userLevel: 1, userPosition: '测试', userStatus: '1', createTime: '2018-08-29'},
           {id:1 ,userCode: 'role005', userPassword: '123456', userName: '汪波', userPhone: 13003504858, userLevel: 1, userPosition: '测试', userStatus: '0', createTime: '2018-08-24'},
           {id:1 ,userCode: 'role006', userPassword: '123456', userName: '汪波', userPhone: 13003504858, userLevel: 1, userPosition: '测试', userStatus: '0', createTime: '2018-08-20'},
           {id:1 ,userCode: 'role007', userPassword: '123456', userName: '郭柿彤', userPhone: 13003504858, userLevel: 1, userPosition: '测试', userStatus: '1', createTime: '2018-08-25'},
           {id:1 ,userCode: 'role008', userPassword: '123456', userName: '汪波', userPhone: 13003504858, userLevel: 1, userPosition: '测试', userStatus: '0', createTime: '2018-08-21'},
           {id:1 ,userCode: 'role009', userPassword: '123456', userName: '汪波', userPhone: 13003504858, userLevel: 1, userPosition: '测试', userStatus: '1', createTime: '2018-08-18'}
       ]
        for(let i = 0; i < this.data.length; i ++) {
            const statusItem = this.status[this.data[i].userStatus];
            this.data[i].statusText = statusItem.text;
            this.data[i].statusType = statusItem.type;
        }

        this.loading = false;
    }


    // 添加方法
    add() {
        this.modalVisible = true;
        this.roleTitle = '新增角色'
        this.isEdit = false; // 不是修改
    }


    // 修改方法
    edit(item) {
        this.modalVisible = true;
        this.roleTitle = '修改角色'
        this.isEdit = true; // 是修改
        this.roleAdd = item;
    }

    // 保存按钮
    save() {
        let objJson = this.roleAdd;
        objJson.createTime = moment(objJson.createTime).format('YYYY-MM-DD')
        console.log(objJson);

        if(this.isEdit) {
            this.http.post(this.baseUrl + '/user/systemUser/update', objJson)
                .subscribe(
                    (val) =>{
                        this.nznot.create('error', '新增成功' , '成功');
                        this.getData();
                    },
                    (error) => {
                        this.nznot.create('error', error.msg , error.msg);
                    });

        } else {
            this.http.post(this.baseUrl + '/user/systemUser/insert', objJson)
                .subscribe(
                    (val) =>{
                        this.nznot.create('error', '新增成功' , '成功');
                        this.getData();
                    },
                    (error) => {
                        this.nznot.create('error', error.msg , error.msg);
                    });
        }
    }

    // 删除方法
    delete(id) {
        console.log(id)
        this.modal.open({
            title: '确认删除',
            content: '是否确认删除该用户',
            okText: '确认',
            cancelText: '取消',
            onOk: () => {
                this.http.post(this.baseUrl + '/user/systemUser/delete', { id : id}).subscribe(
                    (val) =>{
                        this.nznot.create('error', '删除成功' , '删除成功');
                        this.getData();
                    },
                    (error) => {
                        this.nznot.create('error', error.msg , error.msg);
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


