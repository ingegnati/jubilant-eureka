import { NgModule } from '@angular/core'
import { Routes, RouterModule } from '@angular/router'

import { LogoComponent } from './logo/logo.component'

const appRoutes: Routes = [
    {path: '', pathMatch: 'full', redirectTo: '/index'},
    {path: 'index', component: LogoComponent}
]

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
