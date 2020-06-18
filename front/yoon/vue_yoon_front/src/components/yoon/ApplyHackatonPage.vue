<template>
    <v-row style="margin:100px" justify="center">
        <v-col cols="2">
            <!-- 여기에 공모전 데이터를 보여주고 -->
            <v-row>
                <!--
                    <v-col cols="12">
                        <v-file-input outlined type="file" id="file" ref="file" accept="image/*" v-model="chosenFile" v-on:change="handleFileUpload"/>
                    </v-col>
                -->
                <v-col cols="12">img</v-col>
                <v-col cols="12">제목</v-col>
                <v-col cols="12">인원</v-col>
                <v-col cols="12">장소</v-col>
            </v-row>
        </v-col>
        <v-col cols = "10">
        <v-card width="80vw" >
            <v-container>
                <v-card-title class="headline" style="margin:auto"> 참여 PAGE </v-card-title>
                <v-card-text>
                    <v-row>
                        <!-- 신청자 정보 -->
                        <v-col cols="6" md="6" sm="12">
                            <v-text-field  label="아이디어 명*" required></v-text-field>
                        </v-col>
                        <v-col cols="6" md="6" sm="12">
                            <v-text-field  label="팀 명*" required></v-text-field>
                        </v-col>
                        <v-col cols="8" md="8" sm="8">
                            <v-autocomplete v-model="value" :items="items" dense filled label="Filled" ></v-autocomplete>
                        </v-col>
                        <v-col cols="4" md="4" sm="4">
                            <v-text-field type="number" readonly solo-inverted label=" 명*" required></v-text-field>
                        </v-col>
                    <v-col cols="12">
                        <v-textarea 
                        filled auto-grow rows="4" row-height="30" shaped
                        v-model="info" label="Info" required></v-textarea>
                    </v-col>
                    </v-row>
                </v-card-text>
            </v-container>
        </v-card>
        </v-col>
    </v-row>
</template>
<script>
export default {
    data(){
        return{
            items: ['foo', 'bar', 'fizz', 'buzz'],
      values: ['foo', 'bar'],
      value: null,
        }
    }
}
</script>