import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGetCompanyByIDComponent } from './admin-get-company-by-id.component';

describe('AdminGetCompanyByIDComponent', () => {
  let component: AdminGetCompanyByIDComponent;
  let fixture: ComponentFixture<AdminGetCompanyByIDComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminGetCompanyByIDComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminGetCompanyByIDComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
