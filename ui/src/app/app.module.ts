import { BrowserModule } from '@angular/platform-browser'
import { NgModule } from '@angular/core'

import { AppComponent } from './app.component'
import { LogoComponent } from './index/logo/logo.component'
import { AppRoutingModule } from './app-routing.module'
import { HeaderComponent } from './layout/header/header.component'
import { FooterComponent } from './layout/footer/footer.component'
import { DropdownDirective } from './layout/dropdown.directive'
import { IndexComponent } from './index/index.component'
import { MeshesComponent } from './experiments/meshes/meshes.component'

@NgModule({
  declarations: [
    AppComponent,
    LogoComponent,
    HeaderComponent,
    FooterComponent,
    DropdownDirective,
    IndexComponent,
    MeshesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
