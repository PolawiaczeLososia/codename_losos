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

const routes: Routes = [
  {path: '', component: MainSiteComponent},
  {path: 'warehouse', component: WarehouseViewComponent},
];
@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    MainSiteComponent,
    WarehouseViewComponent,
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
