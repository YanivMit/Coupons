import {Category} from './category';
import {Company} from './company';

export class Coupon {
  constructor(public id:number,
              public category:Category,
              public title:String,
              public description:String,
              public startDate:Date,
              public endDate:Date,
              public amount:number,
              public price:number,
              public image: String,
              public company:Company) {
  }
}
