import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Coupon} from '../../../models/coupon';

@Component({
  selector: 'app-admin-get-all-coupons',
  templateUrl: './admin-get-all-coupons.component.html',
  styleUrls: ['./admin-get-all-coupons.component.css']
})
export class AdminGetAllCouponsComponent implements OnInit {

  coupons: Coupon[];

  constructor(private client:HttpClient) { }

  ngOnInit(): void {
    this.client.get<Coupon[]>("http://localhost:8080/admin/coupons").subscribe(
      (result)=>{
        this.coupons = result;
      },
      (error) => {
        alert("not working");
      }
    );
  }

}
