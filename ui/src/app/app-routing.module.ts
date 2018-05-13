import { NgModule } from '@angular/core'
import { Routes, RouterModule } from '@angular/router'

import { IndexComponent } from './index/index.component'
import { MeshesComponent } from './experiments/meshes/meshes.component'

const appRoutes: Routes = [
    {path: '', pathMatch: 'full', redirectTo: '/index'},
    {path: 'index', component: IndexComponent},
    {path: 'meshes', component: MeshesComponent},
]

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
