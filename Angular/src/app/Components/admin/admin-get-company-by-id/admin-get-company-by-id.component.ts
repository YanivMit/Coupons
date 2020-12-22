import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Company} from '../../../models/company';

@Component({
  selector: 'app-admin-get-company-by-id',
  templateUrl: './admin-get-company-by-id.component.html',
  styleUrls: ['./admin-get-company-by-id.component.css']
})
export class AdminGetCompanyByIDComponent implements OnInit {
  company: Company;
  myCompanyID: number;

  constructor(private client:HttpClient) { }

  ngOnInit(): void {

  }

  onClick() : void {

    this.client.get<Company>("http://localhost:8080/admin/companies/" + this.myCompanyID).subscribe(
      (result)=>{
        this.company = result;
      },
      (error) => {
          //alert("not working");
          alert(error.error);
      }
    );
  }

}
