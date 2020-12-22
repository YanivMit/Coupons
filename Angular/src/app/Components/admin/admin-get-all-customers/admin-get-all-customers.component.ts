import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Customer} from '../../../models/customer';
import {Company} from '../../../models/company';

@Component({
  selector: 'app-admin-get-all-customers',
  templateUrl: './admin-get-all-customers.component.html',
  styleUrls: ['./admin-get-all-customers.component.css']
})
export class AdminGetAllCustomersComponent implements OnInit {

  customers: Customer[];

  constructor(private client:HttpClient) { }

  ngOnInit(): void {
    this.client.get<Customer[]>("http://localhost:8080/admin/customers").subscribe(
      (result)=>{
        this.customers = result;
      },
      (error) => {
        alert("not working");
      }
    );
  }

}
