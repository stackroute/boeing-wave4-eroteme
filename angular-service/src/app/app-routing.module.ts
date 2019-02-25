import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { NotifyComponent } from './notify/notify.component';
import { NotificationcardComponent } from './notificationcard/notificationcard.component';
import { NavigationbarComponent } from './navigationbar/navigationbar.component';

const routes: Routes = [
    {
        path: 'home',
        component: HomeComponent
    },

    {
        path: 'auth/login',
        component: LoginComponent
    },
    {
        path: 'signup',
        component: RegisterComponent
    },
    {
        path: 'note',
        component: NotifyComponent
    },
    {
        path:'notificationCard',
        component: NotificationcardComponent  
     },
     {
        path:'navigation',
        component: NavigationbarComponent
     },
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
