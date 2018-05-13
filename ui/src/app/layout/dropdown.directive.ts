import { Directive, ElementRef, Renderer2, HostListener } from '@angular/core'

@Directive({
  selector: '[appDropdown]'
})
export class DropdownDirective {

  constructor(private el: ElementRef, private renderer: Renderer2) { }

  @HostListener('mouseenter') onMouseEnter() {
    this.renderer.addClass(this.el.nativeElement, 'show')
    this.renderer.addClass(this.el.nativeElement.querySelector('.dropdown-menu'), 'show')
  }

  @HostListener('mouseleave') onMouseLeave() {
      this.renderer.removeClass(this.el.nativeElement, 'show')
      this.renderer.removeClass(this.el.nativeElement.querySelector('.dropdown-menu'), 'show')
  }

}
