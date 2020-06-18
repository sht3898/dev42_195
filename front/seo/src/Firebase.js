import * as firebase from 'firebase';
// import firestore from 'firebase/firestore';

// const settings = {timestampsInSnapshots: true};

const config = {
    apiKey: "AIzaSyD1fbtwu1cZTsH9ZkvahFlVI7sDkQgSJZ0",
    authDomain: "pjt-sub3.firebaseapp.com",
    databaseURL: "https://pjt-sub3.firebaseio.com",
    projectId: "pjt-sub3",
    storageBucket: "pjt-sub3.appspot.com",
    messagingSenderId: "429731031112",
    appId: "1:429731031112:web:bcacca2a5204111b5b907d",
    measurementId: "G-XPF73HJPCJ"
}
firebase.initializeApp(config);
// firebase.firestore().settings(settings);
export default firebase;
