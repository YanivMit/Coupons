import {Component, NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './Components/login/login.component';
import { TestComponent } from './Components/test/test.component';
import { PageNotFoundComponent } from './Components/page-not-found/page-not-found.component';
import { HomePageComponent } from './Components/home-page/home-page.component';
import { SignUpComponent } from './Components/sign-up/sign-up.component';
import {AdminComponent} from './Components/admin/admin.component';
import {CustomerComponent} from './Components/customer/customer.component';
import {CompanyComponent} from './Components/company/company.component';
import {CustomerAddCouponComponent} from './Components/customer/customer-add-coupon/customer-add-coupon.component';
import {CustomerGetAllCouponsComponent} from './Components/customer/customer-get-all-coupons/customer-get-all-coupons.component';
import {CustomerGetAllCouponsByCategoryComponent} from './Components/customer/customer-get-all-coupons-by-category/customer-get-all-coupons-by-category.component';
import {CustomerGetAllCouponsByPriceComponent} from './Components/customer/customer-get-all-coupons-by-price/customer-get-all-coupons-by-price.component';
import {CustomerGetCouponComponent} from './Components/customer/customer-get-coupon/customer-get-coupon.component';
import {CompanyAddCouponComponent} from './Components/company/company-add-coupon/company-add-coupon.component';
import {CompanyGetAllCouponsByCategoryComponent} from './Components/company/company-get-all-coupons-by-category/company-get-all-coupons-by-category.component';
import {CompanyGetAllCouponsByMaxPriceComponent} from './Components/company/company-get-all-coupons-by-max-price/company-get-all-coupons-by-max-price.component';
import {AdminAddCompanyComponent} from './Components/admin/admin-add-company/admin-add-company.component';
import {AdminAddCouponComponent} from './Components/admin/admin-add-coupon/admin-add-coupon.component';
import {AdminAddCustomerComponent} from './Components/admin/admin-add-customer/admin-add-customer.component';
import {AdminGetAllCompaniesComponent} from './Components/admin/admin-get-all-companies/admin-get-all-companies.component';
import {AdminGetAllCouponsComponent} from './Components/admin/admin-get-all-coupons/admin-get-all-coupons.component';
import {AdminGetAllCustomersComponent} from './Components/admin/admin-get-all-customers/admin-get-all-customers.component';
import {AdminGetCompanyByIDComponent} from './Components/admin/admin-get-company-by-id/admin-get-company-by-id.component';
import {AdminGetCouponByIDComponent} from './Components/admin/admin-get-coupon-by-id/admin-get-coupon-by-id.component';
import {AdminGetCustomerByIDComponent} from './Components/admin/admin-get-customer-by-id/admin-get-customer-by-id.component';

const routes: Routes = [
  {path:"", redirectTo:"login",pathMatch:"full"},
  {path:"login", component: LoginComponent},
  {path:"test", component: TestComponent},
  {path:"home", component: HomePageComponent},
  {path:"signup", component: SignUpComponent},
  {path:"admin", component: AdminComponent},
  {path:"customer", component: CustomerComponent},
  {path:"company", component: CompanyComponent},
  {path:"customerAddCoupon", component: CustomerAddCouponComponent},
  {path:"customerGetAllCoupons", component: CustomerGetAllCouponsComponent},
  {path:"customerGetAllCouponsByCategory", component: CustomerGetAllCouponsByCategoryComponent},
  {path:"customerGetAllCouponsByPrice", component: CustomerGetAllCouponsByPriceComponent},
  {path:"customerGetCoupon", component: CustomerGetCouponComponent},
  {path:"companyAddCoupon", component: CompanyAddCouponComponent},
  {path:"companyGetAllCouponsByCategory", component: CompanyGetAllCouponsByCategoryComponent},
  {path:"companyGetAllCouponsByMaxPrice", component: CompanyGetAllCouponsByMaxPriceComponent},
  {path:"adminAddCompany", component: AdminAddCompanyComponent},
  {path:"adminAddCoupon", component: AdminAddCouponComponent},
  {path:"adminAddCustomer", component: AdminAddCustomerComponent},
  {path:"adminGetAllCompanies", component: AdminGetAllCompaniesComponent},
  {path:"adminGetAllCoupons", component: AdminGetAllCouponsComponent},
  {path:"adminGetAllCustomers", component: AdminGetAllCustomersComponent},
  {path:"adminGetCompanyByID", component: AdminGetCompanyByIDComponent},
  {path:"adminGetCouponByID", component: AdminGetCouponByIDComponent},
  {path:"adminGetCustomerByID", component: AdminGetCustomerByIDComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
