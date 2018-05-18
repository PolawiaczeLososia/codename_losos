import { SharedModule } from './shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { NavComponent } from './components/nav/nav.component';
import { MainSiteComponent } from './components/main-site/main-site.component';
import { WarehouseViewComponent } from './components/warehouse-view/warehouse-view.component';
import { AddProductComponent } from './components/add-product/add-product.component';
import { AddWarehouseComponent } from './components/add-warehouse/add-warehouse.component';
import { AddCategoryComponent } from './components/add-category/add-category.component';

const routes: Routes = [
  {path: '', component: MainSiteComponent},
  {path: 'warehouse', component: WarehouseViewComponent, children: [
    {path: 'add-product', component: AddProductComponent},
    {path: 'add-warehouse', component: AddWarehouseComponent},
    {path: 'add-category', component: AddCategoryComponent},
  ]},
];
@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    MainSiteComponent,
    WarehouseViewComponent,
    AddProductComponent,
    AddWarehouseComponent,
    AddCategoryComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    SharedModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
