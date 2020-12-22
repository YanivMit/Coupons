import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './Components/login/login.component';
import { TestComponent } from './Components/test/test.component';
import { SignUpComponent } from './Components/sign-up/sign-up.component';
import { PageNotFoundComponent } from './Components/page-not-found/page-not-found.component';
import { HomePageComponent } from './Components/home-page/home-page.component';
import { AdminComponent } from './Components/admin/admin.component';
import { CustomerComponent } from './Components/customer/customer.component';
import { CompanyComponent } from './Components/company/company.component';
import { CustomerAddCouponComponent } from './Components/customer/customer-add-coupon/customer-add-coupon.component';
import { CustomerGetCouponComponent } from './Components/customer/customer-get-coupon/customer-get-coupon.component';
import { CustomerGetAllCouponsByCategoryComponent } from './Components/customer/customer-get-all-coupons-by-category/customer-get-all-coupons-by-category.component';
import { CustomerGetAllCouponsByPriceComponent } from './Components/customer/customer-get-all-coupons-by-price/customer-get-all-coupons-by-price.component';
import { CustomerGetAllCouponsComponent } from './Components/customer/customer-get-all-coupons/customer-get-all-coupons.component';
import { CompanyAddCouponComponent } from './Components/company/company-add-coupon/company-add-coupon.component';
import { CompanyGetAllCouponsByCategoryComponent } from './Components/company/company-get-all-coupons-by-category/company-get-all-coupons-by-category.component';
import { CompanyGetAllCouponsByMaxPriceComponent } from './Components/company/company-get-all-coupons-by-max-price/company-get-all-coupons-by-max-price.component';
import { AdminAddCompanyComponent } from './Components/admin/admin-add-company/admin-add-company.component';
import { AdminGetCompanyByIDComponent } from './Components/admin/admin-get-company-by-id/admin-get-company-by-id.component';
import { AdminGetAllCompaniesComponent } from './Components/admin/admin-get-all-companies/admin-get-all-companies.component';
import { AdminAddCouponComponent } from './Components/admin/admin-add-coupon/admin-add-coupon.component';
import { AdminGetCouponByIDComponent } from './Components/admin/admin-get-coupon-by-id/admin-get-coupon-by-id.component';
import { AdminGetAllCouponsComponent } from './Components/admin/admin-get-all-coupons/admin-get-all-coupons.component';
import { AdminAddCustomerComponent } from './Components/admin/admin-add-customer/admin-add-customer.component';
import { AdminGetCustomerByIDComponent } from './Components/admin/admin-get-customer-by-id/admin-get-customer-by-id.component';
import { AdminGetAllCustomersComponent } from './Components/admin/admin-get-all-customers/admin-get-all-customers.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    TestComponent,
    SignUpComponent,
    PageNotFoundComponent,
    HomePageComponent,
    AdminComponent,
    CustomerComponent,
    CompanyComponent,
    CustomerAddCouponComponent,
    CustomerGetCouponComponent,
    CustomerGetAllCouponsByCategoryComponent,
    CustomerGetAllCouponsByPriceComponent,
    CustomerGetAllCouponsComponent,
    CompanyAddCouponComponent,
    CompanyGetAllCouponsByCategoryComponent,
    CompanyGetAllCouponsByMaxPriceComponent,
    AdminAddCompanyComponent,
    AdminGetCompanyByIDComponent,
    AdminGetAllCompaniesComponent,
    AdminAddCouponComponent,
    AdminGetCouponByIDComponent,
    AdminGetAllCouponsComponent,
    AdminAddCustomerComponent,
    AdminGetCustomerByIDComponent,
    AdminGetAllCustomersComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
