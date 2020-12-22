import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGetAllCompaniesComponent } from './admin-get-all-companies.component';

describe('AdminGetAllCompaniesComponent', () => {
  let component: AdminGetAllCompaniesComponent;
  let fixture: ComponentFixture<AdminGetAllCompaniesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminGetAllCompaniesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminGetAllCompaniesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
