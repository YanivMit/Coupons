import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGetAllCouponsComponent } from './admin-get-all-coupons.component';

describe('AdminGetAllCouponsComponent', () => {
  let component: AdminGetAllCouponsComponent;
  let fixture: ComponentFixture<AdminGetAllCouponsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminGetAllCouponsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminGetAllCouponsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
