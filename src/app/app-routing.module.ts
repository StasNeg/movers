import {Routes, RouterModule} from '@angular/router';
import {MoveMainAddressComponent} from './move-main-adress/move-main-address.component';
import {NgModule} from '@angular/core';
import {RoomItemsMainMenuComponent} from './room-items-main-menu/room-items-main-menu.component';
import {LoginComponent} from './auth/login/login.component';


const appRoutes: Routes = [
  {path: '', component: MoveMainAddressComponent},
  {path: 'address', component: MoveMainAddressComponent},
  {path: 'room', component: RoomItemsMainMenuComponent},
  {path: 'login', component: LoginComponent},
  {path: '**', redirectTo: '/'}
];

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
  }
)
export class AppRoutingModule {
}
