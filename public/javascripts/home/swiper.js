/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 10/17/22
 * Time: 9:54 AM
 * To change this template use File | Settings | File Templates.
 */
$(document).ready(function () {
    var swiper = new Swiper('.swiper-container.two', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        effect: 'coverflow',
        loop: true,
        centeredSlides: true,
        slidesPerView: 'auto',
        coverflow: {
            rotate: 0,
            stretch: 100,
            depth: 150,
            modifier: 1.5,
            slideShadows: false
        }
    });
});