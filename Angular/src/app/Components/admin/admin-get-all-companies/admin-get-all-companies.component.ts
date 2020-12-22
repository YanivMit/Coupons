import { Component, OnInit } from '@angular/core';
import {Company} from '../../../models/company';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-admin-get-all-companies',
  templateUrl: './admin-get-all-companies.component.html',
  styleUrls: ['./admin-get-all-companies.component.css']
})
export class AdminGetAllCompaniesComponent implements OnInit {

  company: Company[];

  constructor(private client:HttpClient) { }

  ngOnInit(): void {
    this.client.get<Company[]>("http://localhost:8080/admin/companies").subscribe(
      (result)=>{
        this.company = result;
      },
      (error) => {
        alert("not working");
      }
    );
  }
}
