<script setup>
import {ref} from 'vue';

// MainChile.vue로 부터 my-name이라고 받아온 것을 받을 거야! 이때 JS 안에서는 camelCase로 쓰자!
const props = defineProps({
    myName : {
        type: String,
        //Required: true 필수로 필요할때
        default: "안효인", //값이 없으면 default값이 출력.
    },
    messageData: {
        type: Object,
    }
});
console.log(props)

//이렇게 event로 부모꺼를 변경할 것을 여러 함수에서 사용할 수 있게 할 수 있다.
//부모에게 넘길 함수명 => chageName
const emit= defineEmits(["chageName"])

const name = ref('');
const modifyName = () => {
    emit("changeName", name.value);
}
</script>

<template>
    <div class="child-item">
        <h3>{{ `${messageData.no}. ${myName}님 ${messageData.msg}` }}</h3>
    </div>
    <div>
        <!-- 위에 선언한 name이랑 연결시킨다. -->
        이름 변경 : <input type="text" v-model="name">
        <button @click="modifyName">변경</button>
    </div>
</template>

<style scoped>
.child-item{
    background-color:aquamarine;
}
</style>