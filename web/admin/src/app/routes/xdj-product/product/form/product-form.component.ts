import {Component, Input, OnInit} from '@angular/core';
import {NzMessageService, NzModalService, NzModalSubject} from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { tap } from 'rxjs/operators';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {environment} from "@env/environment";

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
    loading = false;
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

    types : Array<any>;
    tags : Array<any>;
    introduces : Array<any>;

    baseUrl : string = environment.XDJ_SERVER_URL;

    constructor(private http: _HttpClient,
                private fb: FormBuilder,
                public msg: NzMessageService,
                private subject: NzModalSubject) {}

    ngOnInit() {
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
        }else{
            this.action = 'insert';
            this.productName = '';
            this.priceUnit = '';
            this.isDiscount = this.isDiscounts[0].value;
            this.productDesc = '';
            this.productStatus = this.status[0].value;
        }
        //查询大类
        this.getTypes().subscribe((res:any) =>{
            this.loading = false;
            this.types = res.data;}
        );
        //查询标签
        this.getTags().subscribe((res : any)=>{
            this.loading = false;
            this.tags = res.data;
        });
        //查询介绍
        this.getIntroduces().subscribe((res : any)=>{
            this.loading = false;
            this.introduces = res.data;
        });

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
        this.loading = true;
        return this.http.post(this.baseUrl + '/product/productBigTypeInfo/query', this.q);
    }
    getTags() {
        this.loading = true;
        return this.http.post(this.baseUrl + '/product/productSmallTypeInfo/query', this.q);
    }
    getIntroduces() {
        this.loading = true;
        return this.http.post(this.baseUrl + '/product/productInformation/query', this.q);
    }
    ok() {
        this.subject.next('0');
        this.subject.destroy();
    }

    cancel() {
        this.subject.destroy();
    }

    save() {
        debugger;
        if (this.form.invalid) {
            this.msg.error('数据填写有误,请仔细检查!');
            return;
        }
        this.loading = true;
        //处理标签
        var submitTags = new Array<any>();
        this.smallProductRelationList.forEach(value =>{
            submitTags.push({
                productId : this.productId,
                smalltypeId : value,
            })
        });
        //处理介绍
        var submitIntroduces = new Array<any>();
        this.productInformationRelationList.forEach((value,index) =>{
            submitIntroduces.push({
                productId : this.productId,
                informationId : value,
                informationSeqno : index
            })
        });
        const body = {
            productInfo : {
                productName: this.productName,
                productPrice: this.productPrice,
                bigtypeId: this.bigtypeId,
                productNum: this.productNum,
                priceUnit: this.priceUnit,
                isDiscount: this.isDiscount,
                productDiscount: this.productDiscount,
                productDesc: this.productDesc,
                status: this.productStatus,
            },
            smallProductRelationList : submitTags,
            productInformationRelationList : submitIntroduces
        };
        this.http.post(this.baseUrl + '/product/productInfo/'+ this.action, body).subscribe((data : any)=>{
            if(data.retCode === '000000'){
                setTimeout(() => this.ok(), 500);
            }else{
                this.msg.error(`操作发生异常:` + data.retMsg);
            }
        });
    }
}
