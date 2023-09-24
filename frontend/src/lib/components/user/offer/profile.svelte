<script>
    import { myToken } from "$lib/components/stores";
	import { error } from "@sveltejs/kit";
    export let user;

    let loadTrigger = 0;
    let profile = null;
    $: if(loadTrigger === 1){
        loadTrigger = 2;
    }
    let picEntity = null;
    let picture;

    let token = null;
    let onFirstLoad = true;
    let subToken = myToken.subscribe((value) => {token = value});
    async function fetchData(){
        let uri = "http://127.0.0.1:8080/profile/" + user;
        //if(token != null) uri = uri + "&TOKEN="+ token;
        let response = await fetch(uri, { method:"GET" });
        if(response.ok || response.status >= 200 && response.status < 300){
            await response.json().then(async (value) => {
                profile = value;
                await fetch("http://127.0.0.1:8080/info/"+value.profilePic).then(async(value)=>{
                    await value.json().then(async(value) => {
                        picEntity = await value;
                        picture = picEntity.id;
                        loadTrigger = 1;
                    }).catch(error);
                }).catch(error);
            }).catch(error);
        }
        else throw new error(response.status, response.statusText); 
    }
    if(onFirstLoad){
        onFirstLoad = false;
        fetchData(user);
    }
</script>

<div class="flex flex-col bg-slate-300 rounded-md w-2/3 my-4 mx-auto p-4">
    {#if loadTrigger === 2}
        <div class="w-auto h-20">
            <img class="rounded-3xl" src="http://127.0.0.1:8080/uploads/{picture}" alt="picture of person by the name of {profile.name}">
        </div>
        <div class="flex flex-col pt-4">
            <p>{profile.name}</p>
            <p>{profile.surname}</p>
        </div>
    {/if}
</div>