import { ChangeDetectorRef, Component, OnDestroy, ViewChild, AfterViewInit, HostListener } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';
import { MatSidenav } from '@angular/material/sidenav';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnDestroy, AfterViewInit {

  @ViewChild('snav') snav: MatSidenav;
  windowWidth: number = null;

  mobileQuery: MediaQueryList;


  private _mobileQueryListener: () => void;

  @HostListener('window:resize', ['$event']) onresize(event: any) {
    this.windowWidth = window.innerWidth;
    if (this.windowWidth >= 768) {
      this.snav.open();
    } else {
      this.snav.close();
    }
  }
  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher) {
    this.mobileQuery = media.matchMedia('(max-width: 768px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  ngAfterViewInit() {
    setTimeout(() => {
      this.windowWidth = window.innerWidth;
      if (this.windowWidth >= 768) {
        this.snav.open();
      }
    }, 0);
  }
}

