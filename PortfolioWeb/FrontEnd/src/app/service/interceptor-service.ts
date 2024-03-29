import { HttpEvent, HttpHandler, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { TokenService } from "./token.service";

@Injectable({
     providedIn: 'root'
})
export class InterceptorService {
     constructor(private tokenService: TokenService){}

     Intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
          let intReq = req;
          const token = this.tokenService.getToken();
          if(token != null){
               intReq = req.clone({
                    headers: req.headers.set('Authorization', 'Bearer'+token)
               });
          }
          return next.handle(intReq);
     }
}

export const InterceptorProvider = [{
     provider: HTTP_INTERCEPTORS,
     useClass: InterceptorService,
     multi: true
}];
