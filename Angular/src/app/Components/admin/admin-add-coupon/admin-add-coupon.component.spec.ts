import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddCouponComponent } from './admin-add-coupon.component';

describe('AdminAddCouponComponent', () => {
  let component: AdminAddCouponComponent;
  let fixture: ComponentFixture<AdminAddCouponComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminAddCouponComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAddCouponComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
