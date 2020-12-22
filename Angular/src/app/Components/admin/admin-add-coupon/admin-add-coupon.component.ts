import { Component, OnInit } from '@angular/core';
import {Category} from '../../../models/category';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-admin-add-coupon',
  templateUrl: './admin-add-coupon.component.html',
  styleUrls: ['./admin-add-coupon.component.css']
})
export class AdminAddCouponComponent implements OnInit {

  category: Category[];

  constructor(private client: HttpClient) { }

  ngOnInit(): void {
    this.client.get("/admin/companies");
  }

}
