import {Routes, RouterModule} from '@angular/router';
import {MoveMainAddressComponent} from './move-main-adress/move-main-address.component';
import {NgModule} from '@angular/core';
import {RoomItemsMainMenuComponent} from './room-items-main-menu/room-items-main-menu.component';
import {LoginComponent} from './auth/login/login.component';
import {OrdersComponent} from './orders/orders.component';
import {CalendarComponent} from './calendar/calendar.component';
import {RequestsComponent} from './requests/requests.component';
import {PanelSettingsComponent} from "./panel-settings/panel-settings.component";
import {CustomerComponent} from "./panel-settings/account/customer/customer.component";
import {MoverComponent} from "./panel-settings/account/mover/mover.component";
import {SettingsComponent} from "./panel-settings/settings/settings.component";
import {ForgotPasswordComponent} from "./auth/forgot-password/forgot-password.component";
import {ResetPasswordComponent} from "./auth/reset-password/reset-password.component";
import {RequestComponent} from "./request/request.component";
import {RequestFinishComponent} from "./request-finish/request-finish.component";
import {RegistrationComponent} from "./auth/registration/registration.component";
import {TrucksComponent} from './trucks/trucks.component';


const appRoutes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'requests', component: RequestsComponent},
  {path: 'calendar', component: CalendarComponent},
  {path: 'orders/:date', component: OrdersComponent},
  {path: 'address', component: MoveMainAddressComponent},
  {path: 'room', component: RoomItemsMainMenuComponent},
  {path: 'login', component: LoginComponent},
  {path: 'panel-settings', component: PanelSettingsComponent},
  {path: 'customer', component: CustomerComponent},
  // {path: 'trucks', component: MoverComponent},
  {path: 'trucks', component: TrucksComponent},
  {path: 'settings', component: SettingsComponent},
  {path: 'forgot', component: ForgotPasswordComponent},
  {path: 'reset', component: ResetPasswordComponent},
  {path: 'request', component: RequestComponent},
  {path: 'request/finish', component: RequestFinishComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: '**', redirectTo: '/'}
];

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
  }
)
export class AppRoutingModule {
}
