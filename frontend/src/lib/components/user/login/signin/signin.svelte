<script>
    import {useForm, validators, HintGroup, required, minLength, Hint, email, } from "svelte-use-form";

    const form = useForm();
    let show = false;
    let onRemember = false;
</script>

<form use:form method="POST" action="/SignIn" class="flex flex-col items-center p-5 mt-6">
    <div class="flex flex-col-reverse w-full">
        <input type="EMAIL" name="EMAIL" placeholder="Почта" use:validators={[required, email]} autocomplete="off" class="rounded-xl p-2 my-2 w-4/5 self-center bg-slate-600 text-gray-200 focus:bg-gray-200 focus:text-slate-700 transition-all duration-300">
        <HintGroup>
            <Hint for=EMAIL on="email" class="my-2 text-gray-200 self-center">Введите корректную почту</Hint>
        </HintGroup>
    </div>
    
    <div class="flex flex-col-reverse w-full">
        <div class="flex flex-row my-2 w-4/5 self-center">
            <input type={show ? "text" : "password"} name="PASSWORD" placeholder="Пароль" use:validators={[required, minLength(8)]} autocomplete="off" class="rounded-xl p-2 my-2 w-full self-center bg-slate-600 text-gray-200 focus:bg-gray-200 focus:text-slate-700 transition-all duration-300">
            <div on:click={() => {show = !show}} class="rounded-xl w-10 h-10 ml-5 mt-2 transition-all duration-300 {!show ? "text-gray-200 bg-slate-600" : "bg-gray-200 text-slate-600"}">
            </div>
        </div>
        <Hint for=PASSWORD on="minLength" class="mt-6 mb-2 text-gray-200 self-center">Пароль должен содержать не менее 8 символов</Hint>
    </div>
    <div class="flex flex-row justify-around mt-6 w-full">
        <p class="text-gray-300">Запомнить меня</p>
        <div on:click={() => {onRemember = !onRemember}} class="rounded-xl w-6 transition-all duration-300 {!onRemember ? "text-gray-200 bg-slate-600" : "bg-gray-200 text-slate-600"}">
        </div>
    </div>

    <button disabled={!$form.valid} type="submit" class="rounded-2xl p-3 bg-orange-500 text-gray-200 hover:bg-orange-100 mt-12 mb-2 disabled:bg-slate-600 transition-all duration-300">Войти</button>
</form>