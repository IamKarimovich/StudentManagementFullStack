import config from '../config'
import { useState } from 'react'

export default function Home() {
	const [username,setUsername] = useState("")
	const [password,setPassword] = useState("")

  const login = () => {
    if(username === config.login.username && password === config.login.password) {
      window.open('/sims','_self')
    } else {
      alert('Username or password is wrong')
    }
  }

	return (
		<>
			<div className="page-container">
				<div className="form">
          <h2>SIMS</h2>
					<input value={username} onChange={(e)=>{setUsername(e.target.value)}} type="text" placeholder="Username..." />
					<input value={password} onChange={(e)=>{setPassword(e.target.value)}} type="password" placeholder="Password..." />
					<button onClick={()=>{login()}}>LOGIN</button>
				</div>
			</div>
		</>
	)
}
