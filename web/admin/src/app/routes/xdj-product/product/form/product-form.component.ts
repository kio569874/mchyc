import {Component, Input, OnInit} from '@angular/core';
import {NzMessageService, NzModalService, NzModalSubject, UploadFile} from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {environment} from "@env/environment";
import {HttpSender} from "../../../../xdj-core/net/http.service";
import {Loading} from "../../../../xdj-core/net/loading.model";

@Component({
    selector: 'product-product-form',
    templateUrl: './product-form.component.html'
})
export class ProductProductFormComponent implements OnInit {
    form: FormGroup;
    q: any = {
        page: 1,
        pageSize: 9999
    };
    loading = new Loading();
    status = [
        { text: '启用', value: '0', type: 'success' },
        { text: '停用', value: '1', type: 'error' }
    ];
    isDiscounts = [
        { text: '是', value: '0'},
        { text: '否', value: '1'}
    ];

    @Input() productId_ : string;
    @Input() productName_ : string;
    @Input() productPrice_: number;
    @Input() productNum_: number;
    @Input() priceUnit_ : string;
    @Input() isDiscount_ : string;
    @Input() productDiscount_ : number;
    @Input() productStatus_: string;
    @Input() productDesc_: string;
    @Input() bigtypeId_: string;
    @Input() productUrl_: string;

    action : string;

    productId : string;
    bigtypeId : string;
    productName : string;
    productPrice: number;
    productNum: number;
    priceUnit : string;
    isDiscount : string;
    productDiscount : number;
    productStatus: string;
    productDesc: string;
    smallProductRelationList : Array<string>;
    productInformationRelationList : Array<string>;

    types: Array<any>;
    tags: Array<any>;
    introduces: Array<any>;

    baseUrl: string = environment.XDJ_SERVER_URL;

    uploadUrl: string = this.baseUrl + "/file/upload";
    showUrl: string = this.baseUrl + "/file/show";

    fileList = [
    ];
    previewImage = '';
    previewVisible = false;

    handleChange({ file, fileList }): void {
        const status = file.status;
        if (status === 'done') {
            const find = fileList.find((f) => {
                return file.uid === f.uid;
            });
            find.url = find.response.data;
        } else if (status === 'error') {
            this.msg.error(`${file.name} file upload failed.`);
        }
    }

    handlePreview = (file: UploadFile) => {
        this.previewImage = file.url || file.thumbUrl;
        this.previewVisible = true;
    }

    constructor(private http: HttpSender,
                private fb: FormBuilder,
                public msg: NzMessageService,
                private subject: NzModalSubject) {}

    ngOnInit() {
        const _ths =this;
        if(this.productId_ !== ''){
            this.action = 'update';
            this.productId = this.productId_;
            this.bigtypeId = this.bigtypeId_;
            this.productName = this.productName_;
            this.productPrice = this.productPrice_;
            this.productNum = this.productNum_;
            this.priceUnit =this.priceUnit_;
            this.isDiscount = this.isDiscount_;
            this.productDiscount = this.productDiscount_;
            this.productDesc = this.productDesc_;
            this.productStatus = this.productStatus_;
            try{
                this.productUrl_.split(',').forEach((value, index, array) => {
                    this.fileList.push({
                        status: 'done',
                        url: this.showUrl + "?path=" + encodeURI(value),
                        realUrl: value
                    });
                });
            }catch (e) {
            }
            this.productInformationRelationList = [];
            this.smallProductRelationList = [];
            _ths.getEditTags().then(res => {
                res.data.forEach((value)=>{
                    _ths.smallProductRelationList.push(value.smalltypeId);
                });
                //查询标签
                this.getTags().then((res2 : any)=>{ this.tags = res2.data;});
            });
            _ths.getEditIntroduces().then(res => {
                res.data.forEach((value)=>{
                    _ths.productInformationRelationList.push(value.informationId);
                });
                //查询介绍
                this.getIntroduces().then((res2 : any)=>{  this.introduces = res2.data;});
            });
        }else{
            this.action = 'insert';
            this.productName = '';
            this.priceUnit = '';
            this.isDiscount = this.isDiscounts[0].value;
            this.productDesc = '';
            this.productStatus = this.status[0].value;
            //查询标签
            this.getTags().then((res : any)=>{
                this.tags = res.data;
            });
            //查询介绍
            this.getIntroduces().then((res : any)=>{
                this.introduces = res.data;
            });
        }
        //查询大类
        this.getTypes().then((res:any) =>{
            this.types = res.data;}
        );


        this.form = this.fb.group({
            productName: [null, [Validators.required]],
            bigtypeId: [null, [Validators.required]],
            productPrice: [null, [Validators.required]],
            productNum: [null, [Validators.required]],
            priceUnit: [null, [Validators.required]],
            isDiscount: [null, [Validators.required]],
            productStatus: [null, [Validators.required]],
            productDiscount: [null, []],
            smallProductRelationList: [null, []],
            productInformationRelationList: [null, []],
            productDesc: [null, []],
        }, );
    }
    getTypes() {
        return this.http.post('/product/productBigTypeInfo/query', this.loading,null, this.q.page, this.q.pageSize);
    }
    getTags() {
        return this.http.post('/product/productSmallTypeInfo/query', this.loading,null, this.q.page, this.q.pageSize);
    }
    getIntroduces() {
        return this.http.post('/product/productInformation/query', this.loading,null, this.q.page, this.q.pageSize);
    }
    getEditTags() {
        return this.http.post('/product/productInfo/smallRelation', this.loading,{productId:this.productId});
    }
    getEditIntroduces() {
        return this.http.post('/product/productInfo/infomationRelation', this.loading,{productId:this.productId});
    }
    ok() {
        this.subject.next('0');
        this.subject.destroy();
    }

    cancel() {
        this.subject.destroy();
    }

    save() {
        if (this.form.invalid) {
            this.msg.error('数据填写有误,请仔细检查!');
            return;
        }
        //处理标签
        const submitTags = new Array<any>();
        this.smallProductRelationList.forEach(value =>{
            submitTags.push({
                productId : this.productId,
                smalltypeId : value,
            });
        });
        //处理介绍
        const submitIntroduces = new Array<any>();
        this.productInformationRelationList.forEach((value,index) =>{
            submitIntroduces.push({
                productId : this.productId,
                informationId : value,
                informationSeqno : index
            });
        });
        //图片处理
        const productUrl = this.fileList.map((f) => {
            if(f.url.indexOf(this.showUrl) > 0){
                return f.realUrl;
            }
            return f.url;
        }).join(',');
        const body = {
            productInfo : {
                productId: this.productId,
                productName: this.productName,
                productPrice: this.productPrice,
                bigtypeId: this.bigtypeId,
                productNum: this.productNum,
                priceUnit: this.priceUnit,
                isDiscount: this.isDiscount,
                productDiscount: this.productDiscount,
                productDesc: this.productDesc,
                status: this.productStatus,
                productUrl
            },
            smallProductRelationList : submitTags,
            productInformationRelationList : submitIntroduces
        };
        this.http.post('/product/productInfo/'+ this.action,this.loading, body).then((data: any) => {
            setTimeout(() => this.ok(), 500);
        });
    }
}
