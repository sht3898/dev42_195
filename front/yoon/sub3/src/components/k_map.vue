<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=daa5b23feb83f8ed51c7d3b5cab05ff1&libraries=services"></script>
<template>
    <div id="k_map" style="width:100%; height:500px;">  
    </div>
</template>
<script>
export default {
    name:'k_map',
    props:{
        address:{type:String}
    },
    data(){
        return{
            info: ''
        }
    },
    mounted(){
        console.log('k_map 도착')
        this.init()
    },
    methods:{
        init(){
            var jooso = this.address.split(',')[1]
            var name = this.address.split(',')[2]
            var mapContainer = document.getElementById('k_map')
            var mapOption = {
                center : new kakao.maps.LatLng(36.355211, 127.298437),
                level: 2
            }
            var map = new kakao.maps.Map(mapContainer, mapOption);
            var geocoder = new kakao.maps.services.Geocoder();
            geocoder.addressSearch(jooso,function(result,status){
                if (status === kakao.maps.services.Status.OK){
                    var coords = new kakao.maps.LatLng(result[0].y,result[0].x);
                    var marker = new kakao.maps.Marker({
                        map: map,
                        position: coords
                    });
                    var infowindow = new kakao.maps.InfoWindow({
                        content: '<div style="width:200px;text-align:center;padding:6px">'+ jooso + name + '</div>'
                    });
                    infowindow.open(map, marker);
                    map.setCenter(coords);
                }
            })
        }
    }
}
</script>
