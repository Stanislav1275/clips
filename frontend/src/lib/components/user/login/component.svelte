<script>
    import Signin from "./signin/signin.svelte";
    import Signup from "./signup/signup.svelte";

    const methods = [
        Signin,
        Signup
    ]

    export let action = Signin;   
</script>
<div class="bg-slate-700 rounded-2xl w-3/5 mx-auto self-center align-middle justify-center flex flex-col">
    <ul class="flex flex-row justify-around p-5 rounded-2xl bg-slate-600 mt-8 w-3/5 self-center">
        <li>
            <a href="" on:click={() => ( action = methods[0] )}>Вход</a>
        </li>
        <li>
            <a href="" on:click={() => ( action = methods[1] )}>Регистрация</a>
        </li>
    </ul>
    <svelte:component this={action}/>
    
</div>