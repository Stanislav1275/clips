<script>
    import Login from "$lib/components/user/login/component.svelte";
    export let data;
</script>

<div>
    <Login />
    <div>
        {data.res}
    </div>
</div>

<svelte:head>
	<title>Sign in</title>
	<meta name="description" content="User profile" />
</svelte:head>
